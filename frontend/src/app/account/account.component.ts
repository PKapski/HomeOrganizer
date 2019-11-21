import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../_services/user.service";
import {Router} from "@angular/router";
import {first} from "rxjs/operators";
import {MyErrorStateMatcher, passwordMatchValidator} from "../register/register.component";
import {User} from "../user/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarComponent} from "../snackbar/snackbar.component";
import {AuthService} from "../_services/auth.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  loading = false;
  minPwLength = 5;
  formGroup: FormGroup;
  errorText = null;
  snackBarDuration = 5000;
  editPassword = false;
  currUser: User;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private snackBar: MatSnackBar) {
    if (localStorage.getItem('current_user') == null) {
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(this.minPwLength)]],
      email: ['', [Validators.email, Validators.required]],
      password: [{value: '', disabled: true}, [Validators.required, Validators.minLength(this.minPwLength)]],
      password2: [{value: '', disabled: true}, [Validators.required]],
      firstName: '',
      lastName: '',
      householdId: ''
    }, {validator: passwordMatchValidator});
    this.userService.getUser(localStorage.getItem('current_user')).subscribe(
      data => {
        this.currUser = data;
        this.setValues(data as User);
      },
      error => {
        if (error.toString() == "403") {
          this.router.navigate(['/logout']);

        }
      }
    )
  }

  get username() {
    return this.formGroup.get('username');
  }

  get email() {
    return this.formGroup.get('email');
  }

  get password() {
    return this.formGroup.get('password');
  }

  get password2() {
    return this.formGroup.get('password2');
  }

  setValues(data: User) {
    this.formGroup.setValue({
      username: data.username,
      email: data.email,
      firstName: data.firstName,
      lastName: data.lastName,
      password: '',
      password2: '',
      householdId: ''
    });
  }

  onPasswordInput() {
    if (this.formGroup.hasError('passwordMismatch'))
      this.password2.setErrors([{'passwordMismatch': true}]);
    else
      this.password2.setErrors(null);
  }

  onSubmit() {
    if (this.formGroup.invalid) {
      return;
    }
    this.loading = true;

    this.formGroup.patchValue({username: this.username.value.toString().toLowerCase()});
    if (!this.editPassword) {
      this.password.enable();
      this.password2.enable();
      this.formGroup.patchValue({
        password: this.currUser.password,
        password2: this.currUser.password,
        householdId: this.currUser.householdId
      });
    } else {
      this.formGroup.patchValue({householdId: this.currUser.householdId});
    }
    this.userService.modifyUser(localStorage.getItem('current_user'), this.formGroup.value).pipe(first()).subscribe(
      data => {
        if (localStorage.getItem('current_user') != this.formGroup.get('username').value) {
          localStorage.setItem('current_user', this.formGroup.get('username').value);
        }
        this.loading = false;
        this.snackBar.openFromComponent(SnackbarComponent, {
          duration: this.snackBarDuration,
          data: {msg: "Account edited successfully!", reversible: false},
        });
        this.ngOnInit();
      },
      error => {
        this.loading = false;
        this.errorText = "Username and/or email are already taken!"
        if (error.toString() == "403") {
          AuthService.logout();
        }
      }
    )
  }

  matcher = new MyErrorStateMatcher();

  changePasswordValidation() {
    this.editPassword ? this.password.enable() : this.password.disable();
    this.editPassword ? this.password2.enable() : this.password2.disable();

  }
}
