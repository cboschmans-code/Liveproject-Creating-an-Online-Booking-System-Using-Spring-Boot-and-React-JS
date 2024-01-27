import {Subject} from "rxjs";

const initiateSubject = new Subject();
const paySubject=new Subject();
const confirmSubject = new Subject();
export const billingDetails = {
    hide: () => initiateSubject.next(true),
    onChange: () => initiateSubject.asObservable()
};
export const cardDetails = {
    show: (clientSecret) =>paySubject.next({visible: true, clientSecret: clientSecret}),
    hide: () => paySubject.next({visible:false}),
    onChange: () => paySubject.asObservable()
};

export const confirmDetails = {
    show: (ticket,salonDetails) =>confirmSubject.next({ ticket: ticket, salonDetails: salonDetails}),
    onChange: () => confirmSubject.asObservable()
};