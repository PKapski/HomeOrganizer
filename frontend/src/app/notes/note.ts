export class Note {
  private _id: string;
  private _title: string;
  private _message: string;
  private _recipent: string;
  private _creator: string;
  private _creationDate: string;
  private _householdId: string;
  private _visibleToEveryone: boolean;
  private _expirationDate: string;


  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }

  get recipent(): string {
    return this._recipent;
  }

  set recipent(value: string) {
    this._recipent = value;
  }

  get creator(): string {
    return this._creator;
  }

  set creator(value: string) {
    this._creator = value;
  }

  get creationDate(): string {
    return this._creationDate;
  }

  set creationDate(value: string) {
    this._creationDate = value;
  }

  get householdId(): string {
    return this._householdId;
  }

  set householdId(value: string) {
    this._householdId = value;
  }

  get visibleToEveryone(): boolean {
    return this._visibleToEveryone;
  }

  set visibleToEveryone(value: boolean) {
    this._visibleToEveryone = value;
  }

  get expirationDate(): string {
    return this._expirationDate;
  }

  set expirationDate(value: string) {
    this._expirationDate = value;
  }
}
