import {ChecklistItem} from "./checklistItem";

export class Checklist{
    private _id: string;
    private _title: string;
    private _itemList: ChecklistItem[];
    private _creator: string;
    private _recipent: string;
    private _householdId: string;
    private _creationDate: string;
    private _expirationDate: string;
    private _visibleToEveryone: boolean;

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

  get itemList(): ChecklistItem[] {
    return this._itemList;
  }

  set itemList(value: ChecklistItem[]) {
    this._itemList = value;
  }

  get creator(): string {
    return this._creator;
  }

  set creator(value: string) {
    this._creator = value;
  }

  get recipent(): string {
    return this._recipent;
  }

  set recipent(value: string) {
    this._recipent = value;
  }

  get householdId(): string {
    return this._householdId;
  }

  set householdId(value: string) {
    this._householdId = value;
  }

  get creationDate(): string {
    return this._creationDate;
  }

  set creationDate(value: string) {
    this._creationDate = value;
  }

  get expirationDate(): string {
    return this._expirationDate;
  }

  set expirationDate(value: string) {
    this._expirationDate = value;
  }

  get visibleToEveryone(): boolean {
    return this._visibleToEveryone;
  }

  set visibleToEveryone(value: boolean) {
    this._visibleToEveryone = value;
  }
}
