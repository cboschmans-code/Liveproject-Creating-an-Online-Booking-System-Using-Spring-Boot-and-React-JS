import {ProgressBar} from "react-bootstrap";
import {useEffect, useState} from "react";
import {serviceStore} from "../../store/servicesState";
import {loadingIndicator} from "./loadingstate";

export function LoadingIndicator() {
    const [isLoading, setIsLoading] = useState(false);
    const [progress, setProgress] = useState(0);

    useEffect(() => {
        const subscription = loadingIndicator.onChange().subscribe(
            (value) => setIsLoading(value)
        )
        setInterval(intervalId => {
            setProgress(progress + 50);
            if (progress === 100) {
                clearInterval(intervalId);
                intervalId = null;
            }
            return () => {
                subscription.unsubscribe();
            }
        }, 1)
    }, []);
    return (isLoading && <ProgressBar striped variant="info" now={progress}/>)
}