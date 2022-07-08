<template>
  <div>
    <h1>{{ title }}</h1>

    <button @click="show">product</button>
    <hr>
    <button @click="showStock">stock</button>
    <hr>
    <button @click="refill">refill</button>
    <hr>
    <button @click="decreaseAmount">decrease</button>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";

export default {
  name: 'Home',
  data() {
    return {title: 'sdfsd'}
  },
  mounted() {

  },
  methods: {
    show() {
      try {
        this.$store.dispatch('product/fetchProductById', 2)
            .then(() => {
              console.log('**',this.$store.getters)
              this.title = this.$store.getters['product/getProduct']
            })
      } catch (e) {
        console.log('there is a problem')
      }
    },
    showStock(){
      try {
        this.$store.dispatch('product/fetchStockById',10)
        .then(()=>{
          this.title = this.$store.getters['product/getStock']
        })
      }catch (e){
        console.log('there is a problem in stock')
      }
    },
    refill(){
      try{
        this.$store.dispatch('product/addStockAmount',13)
        .then(()=>{
          this.title = this.$store.getters['product/getProduct']
        })
      }catch(e){
        console.log('there is a problem in refill')

      }
    },
  decreaseAmount(){
    try{
      this.$store.dispatch('product/decreaseStockAmount', 15).then(()=>{
        this.title = this.$store.getters['product/getProduct']
      })
    }catch (e) {
      console.log('decrease amount problem')
    }
  }
  },


}
</script>
