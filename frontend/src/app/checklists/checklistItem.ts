export class ChecklistItem{
  private _message: string;
  private _isChecked: boolean;

  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }

  get isChecked(): boolean {
    return this._isChecked;
  }

  set isChecked(value: boolean) {
    this._isChecked = value;
  }
}
