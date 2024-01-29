import {Subject} from "rxjs";

const subject = new Subject();
export const scanPage = {
    showResult: (scanResult) => subject.next({scanResult:scanResult} ),
    hideResult: () => subject.next({scanResult: undefined}),
    onChange : () => subject.asObservable()
};