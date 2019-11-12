import {ChecklistItem} from "./checklistItem";

export class Checklist{
     id: string;
     title: string;
     itemList: ChecklistItem[];
     creator: string;
     recipent: string;
     householdId: string;
     creationDate: string;
     expirationDate: string;
     visibleToEveryone: boolean;
}
