import axios from 'axios'
import React, {  useState } from 'react'
import '../pages/order.css'

const Stock = () => {
    const[stock,setStock]=useState({name:'',availablequantity:'',cost:''})
    const[error,setError]=useState('');
    const handlechange=((e)=>{
        setStock({...stock,[e.target.name]:e.target.value})
    })
   const handlesubmit=async(e)=>{
    e.preventDefault()
    try{
        const response=await axios.post('http://localhost:8080/api/v2/inventory',stock)
        console.log("added product",response.data)
        setStock({name:'',availablequantity:'',cost:''}
        )
        setError('added product')
       
    }catch(error){
       if(error.response && error.response.status===409){
        setError("product already exist")
       }else{
        setError("failed to add product")
       }
    }

   }
  return (
    <div className='body'>
    <div className='container'>
      <div className='formstyle'>
   <form className='form' onSubmit={handlesubmit}>
      <input name="name" placeholder="Product Name"type='text' onChange={handlechange} />
      <input name="availablequantity" type="number" placeholder="Quantity" onChange={handlechange} />
      <input name="cost" type="number" placeholder="cost" onChange={handlechange} />
     
      <button type="submit" id='submitbutton'>Place Order</button>
    </form>
    { error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
    </div>
    </div>
  )
}

export default Stock