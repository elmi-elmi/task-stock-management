<template>
  <div>
    <v-card class="d-flex align-center flex-column mx-auto pa-4 mb-1" max-width="400px" min-height="300px" outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!product.name"/>
      <DisplayResultComp v-else/>
      <InputAmountComp v-if="product.name"/>

    </v-card>

    <SearchCardComp v-model="id" @search="searchProduct"/>


  </div>

</template>

<script>

import SearchCardComp from "@/components/SearchCardComp";
import InputAmountComp from "@/components/InputAmountComp";
import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";

export default {
  name: 'ProductView',
  components: {SearchCardComp, InputAmountComp, NoSearchFallbackComp,DisplayResultComp},
  data() {
    return {id: null}
  },
  computed: {
    product() {
      return this.$store.getters['product/getProduct']
    }
  },
  methods: {
    searchProduct() {
      try {
        this.$store.dispatch('product/fetchProductById', this.id)
            .then(() => {
              this.id = null
            }).catch((e) => {
          this.$router.push({name: 'notFound'})
        })


      } catch (e) {
        console.log('there is a problem (500)')

      }
    },
    showStock() {
      try {
        this.$store.dispatch('product/fetchStockById', 10)
            .then(() => {
              this.product = this.$store.getters['product/getStock']
            })
            .catch((e) => {
              console.log('---- 404')
              console.log(e)
            })
      } catch (e) {
        console.log('there is a problem in stock (500)')
        console.log(e)
      }
    },

  },


}
</script>


<style lang="scss">


</style>
