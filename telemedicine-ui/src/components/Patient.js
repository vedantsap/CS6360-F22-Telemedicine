import axios from 'axios';
import React from "react";
import Table from './Table';
import DateTimePicker from 'react-datetime-picker';
import "react-datepicker/dist/react-datepicker.css";

class Patient extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            authenticated: false,
            viewedProfile: false,

            userData: [],
            services: [],
            upcoming: [],

            doctorFname: null,
            doctorLname: null,
            serviceName: null,
            timestamp: new Date(),

            appointmentId: null,
        };
    }

    componentDidMount() {
        this.getAllServices();
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

    getServiceHeadings() {
        return Object.keys({
            "serviceName": null,
            "price": null,
            "fname": null,
            "lname": null,
            "specialty": null,
            "email": null
        });
    }

    getAppointmentHeadings() {
        return Object.keys({
            "appointmentId": null,
            "doctorFname": null,
            "doctorLname": null,
            "patientId": null,
            "serviceName": null,
            "timestamp": null
        });
    }

    getAllServices() {
        axios.get('http://localhost:8080/service/getAllServices').then((response) => {
            this.setState({ services: response.data });
        });
    }

    getFutureAppointments(e) {
        e.preventDefault();
        let payload = { username: this.state.username }
        axios.post('http://localhost:8080/appointment/getFuturePatientAppointments', payload).then((response) => {
            console.log(response.data);
            this.setState({
                upcoming: response.data,
            });
        });
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
            console.log(this.state.userData);
            this.getFutureAppointments();
        }
        this.setState({
            viewedProfile: !this.state.viewedProfile
        });
    };

    bookAppointment(e) {
        e.preventDefault()
        let payload = {
            doctorFname: this.state.doctorFname,
            doctorLname: this.state.doctorLname,
            patientId: this.state.username,
            serviceName: this.state.serviceName,
            timestamp: this.state.timestamp,
        };
        axios.post('http://localhost:8080/appointment/bookAppointment', payload)
    }

    cancelAppointment(e) {
        e.preventDefault()
        let payload = {
            appointmentId: this.state.appointmentId,
        };
        axios.post('http://localhost:8080/appointment/cancelAppointment', payload);
    }

    handleLogin(e) {
        e.preventDefault()
        let payload = { username: this.state.username, password: this.state.password, userType: 'PATIENT' };
        axios.post('http://localhost:8080/login/authenticate', payload).then((response) => {
            this.setState({ authenticated: response.data });
            this.getFutureAppointments();
        });

        console.log("Username: " + this.state.username + " Password: " + this.state.password
            + " --- authenticated? --- " + this.state.authenticated);
    };

    handleLogout(e) {
        e.preventDefault()
        this.setState({ authenticated: false })
    }

    render() {
        if (!this.state.authenticated) {
            return <div>
                <form onSubmit={(e) => this.handleLogin(e)}>
                    <h3>"Please login to the Patient's portal"</h3>
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
            <h3>"Welcome to the Patient's portal"</h3>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Available Services</h4>
            <div>
                <Table theadData={this.getServiceHeadings()} tbodyData={this.state.services} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Your Upcoming Appointments</h4>
            <form onSubmit={(e) => this.getFutureAppointments(e)}>
                <input type="submit" value="Refresh" />
            </form>
            <div>
                <Table theadData={this.getAppointmentHeadings()} tbodyData={this.state.upcoming} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Book Your Next Appointment</h4>
            <div>
                <form onSubmit={(e) => this.bookAppointment(e)}>
                    <label for="fname">Doctor First Name:</label>
                    <input type="doctorFname" name="doctorFname"
                        onChange={(e) => this.setState({ doctorFname: e.target.value })}
                    /><br />
                    <label for="fname">Doctor Last Name:</label>
                    <input type="doctorLname" name="doctorLname"
                        onChange={(e) => this.setState({ doctorLname: e.target.value })}
                    /><br />
                    <label for="fname">Service Name:</label>
                    <input type="serviceName" name="serviceName"
                        onChange={(e) => this.setState({ serviceName: e.target.value })}
                    /><br />
                    <label for="fname">Date & Time:</label>
                    <DateTimePicker
                        format={"yyyy-MM-dd HH:mm"}
                        value={this.state.timestamp}
                        onChange={(e) => this.setState({ timestamp: e })} /><br />
                    <input type="submit" value="Book" />
                </form>
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Cancel Appointment</h4>
            <div>
                <form onSubmit={(e) => this.cancelAppointment(e)}>
                    <label for="appointmentId">Enter Appointment ID To Cancel:</label>
                    <input type="appointmentId" name="appointmentId"
                        onChange={(e) => this.setState({ appointmentId: e.target.value })}
                    /><br />
                    <input type="submit" value="Cancel" />
                </form>
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
        </div >;
    }
};

export default Patient;