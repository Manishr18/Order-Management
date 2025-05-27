import { useState } from "react";
import React from "react";
import axios from "axios";
import "../components/orderform.css";
import "bootstrap/dist/css/bootstrap.css";

export const Orderform = () => {
  const [form, setForm] = useState({ name: "", email: "", quantity: "" });
  const [clicked, setClicked] = useState(false);
  const [nameerror, setNameerror] = useState("");
  const [quantityerror, setQuantityerror] = useState("");
  const [emailerror, setEmailerror] = useState("");
  const validateform=()=>{
    let isvalid=true
    setNameerror('')
    setQuantityerror('')
    setEmailerror('')
    if(!form.name.trim()){
        setNameerror("Name is required")
        isvalid=false
    }
    if(!form.quantity){
        setQuantityerror("quantity is required")
        isvalid=false
    }else if(isNaN(form.quantity) || parseInt(form.quantity)<=0){
        setQuantityerror("Quantity must be number")
        isvalid=false
    }
    if(!form.email.trim()){
        setEmailerror("Email is required")
        isvalid=false
    }else if(!/\S+@\S+\.\S+/.test(form.email)){
        setEmailerror("Invalid email")
        isvalid=false
    }
    return isvalid
  }
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };
  const handleclick = () => {
    setClicked(true);
    setTimeout(() => {
      setClicked(false);
    }, 1500);
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    if(validateform()){
    try {
      await axios.post("http://localhost:8080/api/v1/orders", form);
      alert("ORDER PLACED");
    } catch (error) {
      console.error("Error placing order:", error);
      alert("Failed to place order. Check the console.");
    }
}
  };
  const buttonclass = `btn ${clicked ? "btn-success" : "btn-primary"} `;
  return (
    <div className="formstyle">
      <div className="container ">
        <div className="formmiddle">
          <span className="formcenter">
            <form className="form" onSubmit={handleSubmit}>
              <h2>PLACE ORDER</h2>
              <label for="name"></label>
              <br />
              <input
                type="text"
                name="name"
                id="name"
                placeholder="Name"
                onChange={handleChange}
              />
               {nameerror && <div className="error-message">{nameerror}</div>}
              <br />

              <label for="quantity"></label>
              <br />
              <input
                type="number"
                name="quantity"
                id="quantity"
                placeholder="Quantity"
                onChange={handleChange}
              />
               {quantityerror && <div className="error-message">{quantityerror}</div>}
              <br />

              <label for="email"></label>
              <br />
              <input
                type="email"
                name="email"
                id="email"
                placeholder="Email"
                onChange={handleChange}
              />
               {emailerror && <div className="error-message">{emailerror}</div>}
              <br />

              <button
                type="submit"
                id="submitbutton"
                className={buttonclass}
                onClick={handleclick}
              >
                Place Order
              </button>
            </form>
          </span>
        </div>
      </div>
    </div>
  );
};
