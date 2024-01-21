import {useRef, useState} from "react";
import {Button, Col, Form, Row, CardHeader, CardTitle, Card, CardBody, CardText} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {useLocation} from 'react-router-dom';
import {loadingIndicator} from "../loadingindicator/loadingstate";
import {notification} from "../notification/notificationstate";

export function ChooseSlot() {
    const [slots, setSlots] = useState([]);
    const [slotsAreEmpty,setSlotsAreEmpty]=useState(true)
    const {pathname} = useLocation();
    const date = useRef();

    const serviceName = pathname.split('/')[3].split('_').join(' ');
    const serviceId = parseInt(pathname.split('/')[2]);
    const fetchSlots = (id) => {
        setSlots(slots=> []);
        if (new Date().getTime() > new Date(date.current.value).getTime()){
            setSlotsAreEmpty(true);
            notification.showError("date was in the past");
            return;
        }
        loadingIndicator.show()
        fetch(`http://localhost:8080/api/slots/retrieveAvailableSlots/${id}/${date.current.value}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
            }).then(data => {
            console.log(slots)
            setSlotsAreEmpty(false);
            setSlots([ ...data]);
            console.log(data);
            console.log(slots)
            loadingIndicator.hide();
            notification.showSuccess("slots loaded.");
        }).catch((e) => {
            console.log("error :" + e);
            setSlotsAreEmpty(true);
            loadingIndicator.hide();
            notification.showError(e.message);
        });

    }
    return (<Container fluid className="m-xl-5">
            <Form>
                <Row>
                    <Col><Form.Label>Choose a Date for {serviceName}</Form.Label></Col>
                    <Col>
                        <Form.Control className="w-50" type="date" ref={date}/>
                    </Col>
                    <Col style={{display: 'flex', justifyContent: 'left'}}>
                        <Button variant="primary" onClick={() => fetchSlots(serviceId)}>Show Slots</Button>
                    </Col>
                </Row>
            </Form>
            <Container className="mt-xl-5" fluid="lg">
                {!slotsAreEmpty && date.current && <Row><p className="h5 text-start"> Available Slots on {date.current.value}</p></Row>}
                <Row>
                {slots.map((slot, index) => {

                        return (
                            <Col key={slot.id}>
                            <Card className="border-0 mb-4 me-4" key={slot.id} style={{width: '18rem'}}>
                                <CardHeader style={{
                                    backgroundColor: '#EEEEEE',
                                    borderWidth: "2px",
                                    borderStyle: "solid",
                                    borderColor: '#D3D3D3'
                                }}> <CardTitle>{serviceName}</CardTitle></CardHeader>
                                <CardBody style={{
                                    borderStyle: "solid",
                                    borderTopStyle: "none",
                                    borderWidth: "2px",
                                    borderColor: '#D3D3D3'
                                }}>
                                    <CardText className="h2">{slot.stylistName}</CardText>
                                    <Card.Text className=""><small>Slot
                                        Time {new Date(slot.slotFor).toLocaleString('en-US', {
                                            hour: 'numeric',
                                            minute: 'numeric',
                                            hour12: true
                                        })}</small> </Card.Text>
                                    <Button variant="outline-primary">Book This Slot</Button>
                                </CardBody>
                            </Card>
                            </Col>)

                })}</Row></Container>
        </Container>

    );
}
