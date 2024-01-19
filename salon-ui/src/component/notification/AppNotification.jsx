import {Toast, ToastContainer} from "react-bootstrap";
import {notification as appNotification} from "./notificationstate";
import {useEffect, useState} from "react";
export function AppNotification() {

    const [notification, setNotification] = useState({
        show: false,
        title: '',
        variant: '',
        message: '',
    });
    const onNotificationReceived = (res) => {
        setNotification({
            show: true,
            title: res.title,
            variant: res.variant,
            message: res.message
        });
    }
    useEffect(() => {
        const subscription = appNotification.onChange().subscribe(res => {
            onNotificationReceived(res);
        });

        // Clean up: unsubscribe on unmount
        return () => {
            subscription.unsubscribe();
        };
    }, []);

    return (<ToastContainer
            className="p-3"
            position='bottom-center'
            style={{zIndex: 1}}
        >
            <Toast bg={notification.variant} onClose={() => setNotification({
                show: false,
                title: '',
                variant: '',
                message: '',
            }) } show={notification.show} delay={3000} autohide={true}>
                <Toast.Header closeButton={true}>

                    <strong className="me-auto">{notification.title}</strong>

                </Toast.Header>
                <Toast.Body>{notification.message}</Toast.Body>
            </Toast>
        </ToastContainer>
    );
}