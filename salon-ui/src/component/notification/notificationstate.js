import {Subject} from "rxjs";

const subject = new Subject()
export const notification=  {
    showSuccess: () => subject.next( {title:"Success",message:"services loaded",variant:'success'}),
    showError: (message) =>{console.log(message); subject.next( {title:"Error ",  message: message,variant:'danger'})},
    onChange: () => subject.asObservable()
};