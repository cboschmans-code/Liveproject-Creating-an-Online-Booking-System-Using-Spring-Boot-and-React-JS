import logo from './logo.svg';
import './App.css';
import {Navbar} from 'react-bootstrap';
import Container from 'react-bootstrap/Container';

function App() {
  return (
    <div className="App">
      <Navbar expand="lg" className="navbar-dark bg-dark">
        <Container fluid >
          <Navbar.Brand>AR Salon and Day Spa Services</Navbar.Brand>
        </Container>
        </Navbar>
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
