import {useEffect, useState} from "react";
import QrReader from 'modern-react-qr-reader';
import {notification} from "../notification/notificationstate";
import {scanPage} from "./scanstate";
import {Button, Col, Row} from "react-bootstrap";

export function VerifyUser() {
    const [ticketId, setTicketId] = useState(0);
    const [ticket, setTicket] = useState();


    const handleScan = data => {
        if (data) {
            setTicketId(data);
            fetch(`https://192.168.0.212:8443/api/tickets/${data}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                }).then(async ticket => {
                    console.log(ticket);
                    setTicket(await ticket);
                //scanPage.showResult(ticket);
            }).catch((e) => {
                console.log("error :" + e);
                console.log("ticketid " + data);
                notification.showError(e.message);
            });
        }
    }

    const handleError =  error => { /*if (!!error) {
            console.info(error);
            notification.showError(error.message);
        }*/
    }
    return (
        !ticket ? (<QrReader
            onScan={handleScan}
            onError={handleError}
            style={{width: '100%'}}
            delay={300}
            facingMode={"environment"}
        />) : (<>
            <p className={"h2 justify-content-start"}>Details</p>
            <Row>
                <Col className={"justify-content-start"}>
                    <p className={"fw-bold"}>Service Details</p>
                    <p>{ticket.payment.selectedService.name} @ {new Date(ticket.payment.slot.slotFor.substring(0, 9)).toDateString()} By {ticket.payment.slot.stylistName}</p>
                    <p className={"fw-bold  mt-xxl-5 pt-xxl-5"}>User Information</p>
                    <p>{ticket.payment.firstName} {ticket.payment.lastName}</p>
                    <p>{ticket.payment.email}</p>
                    <p>zip {ticket.payment.amount}</p>
                    <p>Phone {ticket.payment.phoneNumber}</p>
                </Col>
                <Col className={"justify-content-start"}>
                    <Button variant={"primary"} onClick={() => setTicket(undefined)}>Scan another</Button>
                </Col>
            </Row>
        </>)

    );
}






