import './App.css';
import {Navbar} from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import {LoadingIndicator} from "./component/loadingindicator/LoadingIndicator";
import {ChooseService} from "./component/services/ChooseService";
import {ChooseSlot} from './component/slots/ChooseSlot';
import {AppNotification} from "./component/notification/AppNotification";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
function App() {
const router = createBrowserRouter([
  {path: '/', element: <ChooseService/>},
  {path: '/chooseslot/:serviceId/:serviceName', element:<ChooseSlot/>}
]);

    return (
        <div className="App">
            <Navbar expand="lg" className="navbar-dark bg-dark">
                <Container fluid>
                    <Navbar.Brand>AR Salon and Day Spa Services</Navbar.Brand>
                </Container>
            </Navbar>
                 <LoadingIndicator/>
          <RouterProvider router={router}/>
            <AppNotification/>
        </div>
    );
}

export default App;
