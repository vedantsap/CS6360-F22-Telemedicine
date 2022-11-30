import axios from 'axios';
import React from "react";
import Table from './Table';

class Admin extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            authenticated: false,
            viewedProfile: false,

            userData: [],
            paymentData: [],
            appointmentData: [],

            paymentId: null,
            cost: null,
            paymentDate: null,
            adminId: null,
            doctorId: null,
        };
    }

    componentDidMount() {
        this.getConfirmedPaymentData();
        this.getUnconfirmedAppointmentData();
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

    getPaymentHeadings() {
        return Object.keys({
            "paymentId": null,
            "cost": null,
            "paymentDate": null,
            "appointmentId": null,
            "adminId": null,
            "adminName": null
        });
    }

    getUnconfirmedPaymentHeadings() {
        return Object.keys({
            "appointmentId": null,
            "appointmentDate": null,
            "serviceId": null,
            "patientId": null,
            "doctorId": null,
        });
    }

    getConfirmedPaymentData() {
        axios.get('http://localhost:8080/admin/getConfirmedPayments').then((response) => {
            this.setState({ paymentData: response.data });
        });
    }

    getUnconfirmedAppointmentData() {
        axios.get('http://localhost:8080/admin/getUnconfirmedAppointments').then((response) => {
            this.setState({ appointmentData: response.data });
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
        }
        this.setState({
            viewedProfile: !this.state.viewedProfile
        });
    };

    handleLogin(e) {
        e.preventDefault()
        let payload = { username: this.state.username, password: this.state.password, userType: 'ADMIN' };
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

    approve(e) {
        e.preventDefault()
        let payload = {
            paymentId: this.state.paymentId,
            cost: this.state.cost,
            appointmentId: this.state.appointmentId,
            adminId: this.state.username,
        };
        axios.post('http://localhost:8080/admin/approvePayment', payload);
    }

    render() {
        if (!this.state.authenticated) {
            return <div>
                <form onSubmit={(e) => this.handleLogin(e)}>
                    <h3>"Please login to the Admin's portal"</h3>
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
            <h3>Welcome to the Admin's portal</h3>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Confirmed Payments</h4>
            <div>
                <Table theadData={this.getPaymentHeadings()} tbodyData={this.state.paymentData} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Unconfirmed Payments</h4>
            <div>
                <Table theadData={this.getUnconfirmedPaymentHeadings()} tbodyData={this.state.appointmentData} />
            </div>
            <hr style={{ color: 'black', height: 5 }} />


            <h4>Approve Payments?</h4>
            <div>
                <form onSubmit={(e) => this.approve(e)}>
                    <label for="fname">Payment ID:</label>
                    <input type="paymentId" name="paymentId"
                        onChange={(e) => this.setState({ paymentId: e.target.value })}
                    /><br/>
                    <label for="fname">Amount Paid:</label>
                    <input type="cost" name="cost"
                        onChange={(e) => this.setState({ cost: e.target.value })}
                    /><br />
                    <label for="fname">Appointment ID:</label>
                    <input type="appointmentId" name="appointmentId"
                        onChange={(e) => this.setState({ appointmentId: e.target.value })}
                    /><br />
                    <input type="submit" value="Approve" />
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

export default Admin;