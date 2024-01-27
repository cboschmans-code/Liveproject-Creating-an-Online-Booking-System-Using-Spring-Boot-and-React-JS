import Container from "react-bootstrap/Container";
import {GetBillingDetails} from "./GetBillingDetails";
import {PayWithStripe} from "./PayWithStripe";
import {Elements} from "@stripe/react-stripe-js";
import {loadStripe} from '@stripe/stripe-js';
import {ShowConfirmedTicket} from "./ShowConfirmedTicket";

const stripePromise = loadStripe("pk_test_51M0PdnLQ5jDHJ6jHn9in8FBxKEKM9VZUpqWnoJcHXMuxCwNP46BzXbtQO61YrtQvB1CSGxtvGVWANVSY70YW6gXA009JANvmSC");

export function PaymentContainer() {
    return (<Container fluid className="m-xl-5 text-start">
        <GetBillingDetails/>
        <Elements stripe={stripePromise}><PayWithStripe/></Elements>
        <ShowConfirmedTicket/>
    </Container>);
}