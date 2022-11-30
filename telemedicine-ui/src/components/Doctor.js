import axios from 'axios';
import React from "react";
import Table from './Table';

class Doctor extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            authenticated: false,

            userData: [],
            upcoming: [],
            completed: [],
        };
    }

    getProfileHeadings() {
        return Object.keys({
            "userId": null,
            "password": null,
            "fname": null,
            "minit": null,
            "lname": null,
            "email": null
        });
    }

    getAppointmentHeadings() {
        return Object.keys({
            "appointmentId": null,
            "patientFname": null,
            "patientLname": null,
            "serviceName": null,
            "timestamp": null,
        });
    }

    handleLogin(e) {
        e.preventDefault()
        let payload = { username: this.state.username, password: this.state.password, userType: 'DOCTOR' };
        axios.post('http://localhost:8080/login/authenticate', payload).then((response) => {
            this.setState({ authenticated: response.data });
        });

        console.log("Username: " + this.state.username + " Password: " + this.state.password
            + " --- authenticated? --- " + this.state.authenticated);
    };

    handleLogout(e) {
        e.preventDefault()
        this.setState({ authenticated: false })
    }

    togglePop = () => {
        if (!this.state.viewedProfile) {
            let payload = { username: this.state.username }
            axios.post('http://localhost:8080/superuser/getUserProfile', payload).then((response) => {
                console.log(response.data);
                this.setState({
                    userData: response.data,
                });
            });
            let doctorPayload = { doctorId: this.state.username }
            axios.post('http://localhost:8080/doctor/getAllUpcomingAppointments', doctorPayload).then((response) => {
                console.log(response.data);
                this.setState({
                    upcoming: response.data,
                });
            });
            axios.post('http://localhost:8080/doctor/getAllPastAppointments', doctorPayload).then((response) => {
                console.log(response.data);
                this.setState({
                    completed: response.data,
                });
            });
            console.log(this.state.userData);
        }
        this.setState({
            viewedProfile: !this.state.viewedProfile
        });
    };

    render() {
        if (!this.state.authenticated) {
            return <div>
                <h3>"Please login to the Doctor's portal"</h3>
                <form onSubmit={(e) => this.handleLogin(e)}>
                    <input
                        type="text"
                        name="Username"
                        onChange={(e) => this.setState({ username: e.target.value })}
                    />
                    <input
                        type="password"
                        name="Password"
                        onChange={(e) => this.setState({ password: e.target.value })}
                    />
                    <input type="submit" value="Submit" />
                </form>
            </div>
        }
        return <div>
            <h3>"Welcome to the Doctor's portal"</h3>
            <button onClick={(e) => this.handleLogout(e)}>Logout</button>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Your Upcoming Appointments</h4>
            <div>
                <Table theadData={this.getAppointmentHeadings()} tbodyData={this.state.upcoming} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Your Completed Appointments</h4>
            <div>
                <Table theadData={this.getAppointmentHeadings()} tbodyData={this.state.completed} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />

            <div>
                <div className="btn" onClick={this.togglePop}>
                    <button>View Profile</button>
                </div>
                {this.state.viewedProfile ?
                    <div className="modal">
                        <div className="modal_content">
                            <span className="close" onClick={() => this.toggle}>&times;</span>
                            <p>{this.state.fname}</p>
                            <Table theadData={this.getProfileHeadings()} tbodyData={this.state.userData} />
                        </div>
                    </div>
                    : null
                }
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <button onClick={(e) => this.handleLogout(e)}>Logout</button>
        </div>;

    }
};

export default Doctor;