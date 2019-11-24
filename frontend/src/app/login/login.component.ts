import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from "../_services/user.service";
import {AuthService} from "../_services/auth.service";
import {Router} from "@angular/router";

/** Error when invalid control is dirty, touched, or submitted. */


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loading=false;
  error=false;
  formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private authService: AuthService,
                private router: Router) {
  }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  get username() {return this.formGroup.get('username');}
  get password() { return this.formGroup.get('password'); }

  onSubmit(){
    this.loading=true;

    this.formGroup.patchValue({username: this.username.value.toString().toLowerCase()});

    this.authService.authenticate(this.formGroup.value).subscribe(
      data=>{
        this.router.navigate(['/']);
      },
      error=>{
        this.error=true;
        this.loading=false;
      }
    );
  }
}
