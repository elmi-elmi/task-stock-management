import axios from 'axios';


/*=======================================================
    API CALL WITH AXIOS
 =======================================================*/
const api = axios.create({
    baseURL:'http://localhost:8080/api',
    withCredentials:false,
    headers:{
        Accept:'application/json',
        'Content-Type': 'application/json'
    }
})

export default{

    /*=======================================================
        GET REQUESTS (/product/)
     =======================================================*/
    /*
    *  GET request
    *  $ curl -s -i -w "\n" -X GET localhost:8080/api/product/1
    */
    getProductById(id){
        return api.get(`/product/${id}`)
    },

    /*
     *  GET request
     *  $ curl -s -i -w "\n" -X GET localhost:8080/api/product/1/stock
     */
    getStockById(id){
        return api.get(`/product/${id}/stock`,13)

    },


    /*=======================================================
       PUT REQUESTS (/product/)
      =======================================================*/

    /*
   *  PUT request
   *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/refill?amount=5
   */
    putRefillProduct(id,amount){
        return api.put(`/product/${id}/refill?amount=${amount}`)
    },


    /*
   *  PUT request
   *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/buy?amount=3
   */
    putDecreaseProduct(id, amount){
        return api.put(`/product/${id}/buy?amount=${amount}`)
    }

}

