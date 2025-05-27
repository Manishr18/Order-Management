import React, { useEffect, useState } from "react";
import { Orderform } from "../components/orderform";
import "bootstrap/dist/css/bootstrap.css";
import { useNavigate } from "react-router-dom";
import "./order.css";
import { History } from "../pages/History";
import axios from "axios";

const Home = () => {
  const [showorder, setShoworder] = useState(false);
  const [totalorder, setTotalorder] = useState(0);
  const [displayorders, setDisplayorders] = useState([]);
  const navigate = useNavigate();
  const handlehistory = () => {
    navigate("/History");
  };
  const orderclick = () => {
    navigate("/Orderform");
    setShoworder(true);
  };
  const orderclose = () => {
    setShoworder(false);
  };
  useEffect(() => {
    const fetchorders = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/allorders",
          displayorders
        );
        setTotalorder(response.data.length);
        const fiveorders = response.data.slice(-5).reverse();

        setDisplayorders(fiveorders);
        console.log(displayorders);
      } catch {
        console.error("failed to load orders");
      }
    };
    fetchorders();
  }, []);

  return (
    <div className="background">
      <div className="container text-center">
        <div className="navbar">
          <h3> ORDER SERVICE</h3>
        </div>
        {showorder && <Orderform onclose={orderclose} />}
        <div className="boxstyle">
          <span className="orderbox">
            <div className="totalorder">
              <h4>Total orders</h4>
              <div className="totalnumber">{totalorder}</div>
            </div>
          </span>
        </div>
        <div className="ordercard">
          <div className="recentorders">
            <h2>Recent Orders</h2>

            <table className="ordertable">
              <thead>
                <tr>
                  <th>Order ID</th>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Quantity</th>
                  <th>Status</th>
                  <th>Email</th>
                  <th>Message</th>
                </tr>
              </thead>
              <tbody>
                {displayorders.length === 0 ? (
                  <tr>
                    <td colSpan="7" className="text-center py-10">
                      No orders available
                    </td>
                  </tr>
                ) : (
                  displayorders.map((order) => (
                    <tr key={order.orderId}>
                      <td>{order.orderId}</td>
                      <td>{order.name}</td>
                      <td>{order.price}</td>
                      <td>{order.quantity}</td>
                      <td>{order.status}</td>
                      <td>{order.email}</td>
                      <td>{order.message}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>

            <button
              type="button"
              className="btn btn-primary btn-sm historybutton "
              onClick={handlehistory}
            >
              Order History
            </button>
          </div>

          <div className="placeorder">
            <i class="fa-solid fa-box-open fa-6x boxicon"></i>
            <h3>Place a new order</h3>
            <button
              type="button"
              className="btn btn-primary btn-sm orderbutton "
              onClick={orderclick}
            >
              Create Order
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Home;
