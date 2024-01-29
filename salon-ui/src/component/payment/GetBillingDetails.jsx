import {useEffect, useRef, useState} from "react";
import {Button, Form, FormControl, FormGroup, FormLabel} from "react-bootstrap";
import {useLocation} from "react-router-dom";
import {loadingIndicator} from "../loadingindicator/loadingstate";
import {billingDetails, cardDetails} from "./paystate";
import {notification} from "../notification/notificationstate";

export function GetBillingDetails() {
    const [isInitiated, setIsInitiated] = useState(false);
    const {pathname} = useLocation();
    const firstName = useRef();
    const lastName = useRef();
    const email = useRef();
    const phoneNumber = useRef();
    const serviceId = parseInt(pathname.split('/')[3]);
    const slotId = parseInt(pathname.split('/')[2]);


    useEffect(() => {
        const subscription = billingDetails.onChange().subscribe(
            (value) => setIsInitiated(value)
        )
        return () => {
            subscription.unsubscribe();
        }

    }, []);

    async function initiatePayment() {
        fetch(`https://localhost:8443/api/payments/initiate`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            }, body: JSON.stringify({
                slotId: slotId, salonServiceDetailId: serviceId,
                firstName: firstName.current.value, lastName: lastName.current.value,
                email: email.current.value, phoneNumber: phoneNumber.current.value
            })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
            }).then(data => {
            const {clientSecret} = data;
            billingDetails.hide()
            cardDetails.show(clientSecret);

        }).catch((e) => {
            console.log("error :" + e);
            notification.showError(e.message);
        });
    }

    return (!isInitiated && (<> <p className={"h3 justify-content-start"}>Enter Billing Details</p>
            <Form className={"justify-content-start"}>
                <FormGroup>
                    <FormLabel>First Name</FormLabel>
                    <FormControl type={"text"} ref={firstName}/>
                </FormGroup>
                <FormGroup>
                    <FormLabel>Last Name</FormLabel>
                    <FormControl type={"text"} ref={lastName}/>
                </FormGroup>
                <FormGroup>
                    <FormLabel>Email</FormLabel>
                    <FormControl type={"email"} ref={email} placeholder="name@example.com"/>
                </FormGroup>
                <FormGroup>
                    <FormLabel>Phone Number</FormLabel>
                    <FormControl type={"tel"} ref={phoneNumber} />
                </FormGroup>
                <Button onClick={initiatePayment} variant={"primary"}>Make Payment</Button>
            </Form></>
    ));
}