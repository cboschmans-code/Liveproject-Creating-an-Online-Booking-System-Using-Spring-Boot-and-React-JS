import './App.css';
import {Navbar} from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import {useState} from "react";
import {LoadingIndicator} from "./component/loadingindicator/LoadingIndicator";
import {ChooseService} from "./component/ChooseService";
import {useSharedState} from "./hooks/useSharedState";
import {AppNotification} from "./component/notification/AppNotification";

function App() {

    return (
        <div className="App">
            <Navbar expand="lg" className="navbar-dark bg-dark">
                <Container fluid>
                    <Navbar.Brand>AR Salon and Day Spa Services</Navbar.Brand>
                </Container>
            </Navbar>
                 <LoadingIndicator/>  <ChooseService/>
            <AppNotification/>
        </div>
    );
}

export default App;
