import Container from "react-bootstrap/Container";
import {CardHeader, CardTitle, Card, CardBody, CardText, Row, Button} from "react-bootstrap";
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import {loadingIndicator} from "../loadingindicator/loadingstate";
import {notification} from "../notification/notificationstate";

export function ChooseService() {
const [services,setServices]=useState([]);
const navigate = useNavigate();

    const fetchServices = () => {
        // Subscribe to the observable and store the subscription


            loadingIndicator.show()
            fetch("http://localhost:8080/api/services/retrieveAvailableSalonServices")
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }

                }).then(data => {
                console.log(data);
                setServices([...services,...data]);
                loadingIndicator.hide();
                notification.showSuccess("services loaded.");
            }).catch((e) =>{
                console.log("error :"+e);
                loadingIndicator.hide();
                notification.showError(e.message);
            });

    }
    useEffect(() => {
        fetchServices();
    }, []);

    function bookHandler(id, name){
    const underscoredName=name.split(' ').join('_')
    navigate(`/chooseslot/${id}/${underscoredName}`);
    }
    return (
        <Container fluid className="mt-xl-5">
            {services.map((service, index) => {
                if (index % 3 === 0) {
                    return (<Row className="justify-content-md-center" key={service.id}>
                        <Card className="border-0 mb-4 me-4" key={service.id} style={{width: '18rem'}}>
                            <CardHeader style={{
                                backgroundColor: '#EEEEEE',
                                borderWidth: "2px",
                                borderStyle: "solid",
                                borderColor: '#D3D3D3'
                            }}> <CardTitle>{service.name}</CardTitle></CardHeader>
                            <CardBody style={{
                                borderStyle: "solid",
                                borderTopStyle: "none",
                                borderWidth: "2px",
                                borderColor: '#D3D3D3'
                            }}>
                                <CardText className="h2">${service.price}</CardText>
                                <Card.Text className="mb-0">{service.description} </Card.Text>
                                <Card.Text>{service.timeInMinutes} Minutes</Card.Text>
                              <Button variant="outline-primary" onClick={() =>{bookHandler(service.id,service.name)}}>Book Now</Button>
                            </CardBody>
                        </Card>
                        <Card className="border-0 mb-4 me-4" key={services[index + 1].id}
                              style={{width: '18rem'}}>
                            <CardHeader style={{
                                backgroundColor: '#EEEEEE',
                                borderWidth: "2px",
                                borderStyle: "solid",
                                borderColor: '#D3D3D3'
                            }}>
                                <CardTitle>{services[index + 1].name}
                                </CardTitle></CardHeader>
                            <CardBody style={{
                                borderStyle: "solid",
                                borderTopStyle: "none",
                                borderWidth: "2px",
                                borderColor: '#D3D3D3'
                            }}>
                                <CardText className="h2">${services[index + 1].price}</CardText>
                                <Card.Text className="mb-0">{services[index + 1].description} </Card.Text>
                                <Card.Text>{services[index + 1].timeInMinutes} Minutes</Card.Text>
                                <Button variant="outline-primary" onClick={() =>{bookHandler(services[index+1].id,services[index+1].name)}}>Book Now</Button>
                            </CardBody>
                        </Card>
                        <Card className="border-0 mb-4 me-4" key={services[index + 2].id}
                              style={{width: '18rem'}}>
                            <CardHeader style={{
                                backgroundColor: '#EEEEEE',
                                borderWidth: "2px",
                                borderStyle: "solid",
                                borderColor: '#D3D3D3'
                            }}>
                                <CardTitle>{services[index + 2].name}
                                </CardTitle></CardHeader>
                            <CardBody style={{
                                borderStyle: "solid",
                                borderTopStyle: "none",
                                borderWidth: "2px",
                                borderColor: '#D3D3D3'
                            }}>
                                <CardText className="h2">${services[index + 2].price}</CardText>
                                <Card.Text className="mb-0">{services[index + 2].description} </Card.Text>
                                <Card.Text>{services[index + 2].timeInMinutes} Minutes</Card.Text>
                                <Button variant="outline-primary" onClick={() =>{bookHandler(services[index+2].id,services[index+2].name)}}>Book Now</Button>
                            </CardBody>
                        </Card></Row>)
                }
            })}
        </Container>)
}

