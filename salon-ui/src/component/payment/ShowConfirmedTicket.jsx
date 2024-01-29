import {Col, Row} from "react-bootstrap";
import {useEffect, useState} from "react";
import {cardDetails, confirmDetails} from "./paystate";
import QRCode from "react-qr-code";

export function ShowConfirmedTicket() {
    const [ticket, setTicket] = useState();
    const [salonDetails, setSalonDetails] = useState();

    useEffect(() => {
        const subscription = confirmDetails.onChange().subscribe(
            (value) => {
                const {ticket, salonDetails} = value;
                setTicket(ticket);
                setSalonDetails(salonDetails);
            });
        return () => {
            subscription.unsubscribe();
        }
    }, []);


    return (ticket && salonDetails && (<>
        <p className={"h2 justify-content-start"}>Your Ticket Details</p>
        <Row>
            <Col className={"justify-content-start"}>
                <p className={"fw-bold"}>Service Details</p>
                <p>{ticket.payment.selectedService.name} @ {new Date(ticket.payment.slot.slotFor.substring(0, 9)).toDateString()} By {ticket.payment.slot.stylistName}</p>
                <p className={"fw-bold  mt-xxl-5 pt-xxl-5"}>Salon Address Details</p>
                <p>{salonDetails.name}</p>
                <p>{salonDetails.address}</p>
                <p>{salonDetails.city}</p>
                <p>{salonDetails.state}</p>
                <p>{salonDetails.zipCode}</p>
                <p>{salonDetails.phoneNumber}</p>
            </Col>
            <Col className={"justify-content-start"}>
                <p className={"fw-bold"}>Take a Picture of the below code and present it to admin</p>
                <QRCode className={"float-start"} value={`${ticket.id}`} size={100}/>
            </Col>
        </Row>
    </>));
}