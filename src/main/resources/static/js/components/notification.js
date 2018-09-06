import React from 'react';

export default class Notification extends React.Component {
    render() {
        return (
            <div className="notification is-primary">
                <button className="delete"></button>
                This is notification rendering by React
            </div>
        )
    }
}