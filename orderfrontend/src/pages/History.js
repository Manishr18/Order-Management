import React, { useEffect,useState }  from 'react'

import axios from 'axios';
const History = () => {
    const[orderhistory,setOrderhistory]=useState([]);
  
    useEffect(()=>{
        axios.get('http://localhost:8080/api/v1/allorders',orderhistory)
        .then(response=>setOrderhistory(response.data))
        .catch(error=>console.error("failed to load order history"));
        
    },[orderhistory])
   
    
  return (
    <div>
        <h2>ORDER HISTORY</h2>
        {orderhistory.length===0 ?
        (<p>No orders found</p>):(
            <table className='contenttable'>
                <thead>
                    <tr>
                        <th>order id</th>
                        <th>name</th>
                        <th>price</th>
                        <th>quantity</th>
                        <th>status</th>
                        <th>email</th>
                        <th>message</th>

                    </tr>
                </thead>
                <tbody>
                    {orderhistory.map(order=>(
                        <tr key={order.orderid}>
                            <td>{order.orderId}</td>
                            <td>{order.name}</td>
                            <td>{order.quantity}</td>
                            <td>{order.price}*{order.quantity}</td>
                            <td>{order.status}</td>
                            <td>{order.message}</td>
                            <td>{order.email}</td>
                           
                        </tr>
                        
                    ))}

                </tbody>
                    
            </table>
            
        )
        
        }
       
      
    </div>
  )
}

export default History