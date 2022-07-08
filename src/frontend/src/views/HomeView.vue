<template>
  <div>
  <v-card
      class="d-flex align-center flex-column mx-auto pa-4 mb-1"
      max-width="400px"
      min-height="300px"
      outlined
      elevation="18"
  >
    <v-list-item three-line
                 class="mb-12"
    >
      <v-list-item-content>
        <v-card
            class="d-flex align-center justify-space-between mb-2"
            flat
            tile
        >
          <div class="text-overline ">
            Product
          </div>
          <div class="text-overline ">
            {{ $store.getters['product/getProduct'].id }}
          </div>
        </v-card>
        <v-list-item-title class="text-h5 mb-1">
          {{ $store.getters['product/getProduct'].name }}
        </v-list-item-title>
        <v-list-item-subtitle>{{ $store.getters['product/getProduct'].stock }}</v-list-item-subtitle>
      </v-list-item-content>

    </v-list-item>

    <v-card
        class="d-flex justify-center mb-2 " flat tile
    >




      <v-btn
          v-if="title"
          class="mx-2" fab dark small color="teal" @click="refill"
      >
        <v-icon dark>
          mdi-plus
        </v-icon>
      </v-btn>


      <v-btn
          v-if="title"
          class="mx-2" fab dark small color="pink" @click="decreaseAmount"
      >
        <v-icon dark>
          mdi-minus
        </v-icon>

      </v-btn>
    </v-card>
    <div
        class="search-somthing"
    >
    <p>Search Products</p>
      <v-icon
          v-if="!title"
          x-large
          color="green darken-2"
      >
        mdi-magnify
      </v-icon>
    </div>

  </v-card>

    <v-card
        class="mx-auto pa-4 d-flex align-center flex-column "
        max-width="400px"
        min-height="120px"
        outlined
        elevation="18"
    >

        <v-text-field
            class="mb-4"
            v-model.number="id"
            label="Enter Id"
            light
            flat
            clearable
            clear-icon="mdi-close-circle-outline"

        ></v-text-field>

        <v-btn
            :disabled="!id"
            outlined   text @click="search"
        >
          Search
        </v-btn>



    </v-card>
  </div>

</template>

<script>
import ProductService from "@/services/ProductService";

export default {
  name: 'Home',
  data() {
    return {title: null, id:null}
  },
  mounted() {

  },
  methods: {
    search() {
      try {
        this.$store.dispatch('product/fetchProductById', this.id)
            .then(() => {
              this.title = this.$store.getters['product/getProduct']
              this.id = null
            })

      } catch (e) {
        console.log('there is a problem')
      }
    },
    showStock() {
      try {
        this.$store.dispatch('product/fetchStockById', 10)
            .then(() => {
              this.title = this.$store.getters['product/getStock']
            })
      } catch (e) {
        console.log('there is a problem in stock')
      }
    },
    refill() {
      try {
        this.$store.dispatch('product/addStockAmount', 13)
            .then(() => {
              this.title = this.$store.getters['product/getProduct']
            })
      } catch (e) {
        console.log('there is a problem in refill')

      }
    },
    decreaseAmount() {
      try {
        this.$store.dispatch('product/decreaseStockAmount', 15).then(() => {
          this.title = this.$store.getters['product/getProduct']
        })
      } catch (e) {
        console.log('decrease amount problem')
      }
    }
  },


}
</script>


<style lang="scss">

.search-somthing{
  text-align: center;
  position: absolute;
  top:70%;
  left:50%;
  transform: translate(-50%,-50%);
  opacity: 50%;
}


</style>
