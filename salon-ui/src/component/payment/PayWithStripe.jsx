import {useEffect, useState} from "react";
import {Button, Form} from "react-bootstrap";
import {CardElement, useElements, useStripe} from "@stripe/react-stripe-js";
import {billingDetails, cardDetails, confirmDetails} from "./paystate";
import {notification} from "../notification/notificationstate";

export function PayWithStripe() {
    const [isPaying, setIsPaying] = useState(false);
    const [clientSecret, setClientSecret] = useState("");
    const elements=useElements();
    const stripe= useStripe();

    useEffect(() => {
        const subscription = cardDetails.onChange().subscribe(
            (value) => {
                setIsPaying(value.visible);
                setClientSecret(value.clientSecret)
            }
        )
        return () => {
            subscription.unsubscribe();
        }

    }, []);

    const submitHandler= async (e)=> {
        e.preventDefault();
        if(!elements || !stripe){
            return;
        }
        const {paymentIntent} = await stripe.confirmCardPayment(
            clientSecret, {
                payment_method: {
                    card: elements.getElement(CardElement),
                }
            }
        );
        fetch(`http://localhost:8080/api/payments/confirm/${paymentIntent.id}`, {
            method: "PUT"})     .then(response => {
            if (response.ok) {
                return response.json();
            }
        }).then(data => {
            const {salonDetails,ticket} = data;
            cardDetails.hide();
            confirmDetails.show(ticket, salonDetails);

        }).catch((e) => {
            console.log("error :" + e);
            notification.showError(e.message);
        });

    }

    return (isPaying && (<><p className={"h3 justify-content-start"}>Enter Card Details</p>
        <Form onSubmit={submitHandler}>
            <CardElement/>
            <Button type={"submit"} variant={"success"} >Pay</Button>
        </Form></>));
}