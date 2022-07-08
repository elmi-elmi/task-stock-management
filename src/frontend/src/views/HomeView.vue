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
          class="d-flex justify-center align-center mb-2 " flat tile
      >
        <v-text-field
            v-if="title"
            class="mb-4"
            v-model="amount"
            label="Refill or Decrease Stock"
            light
            flat
            type="number"

        ></v-text-field>
        <v-btn
            :disabled="!amount"
            class="mx-2" fab dark small color="teal" @click="refill"
        >
          <v-icon dark>
            mdi-plus
          </v-icon>
        </v-btn>


        <v-btn
            :disabled="!amount"
            class="mx-2" fab dark small color="pink" @click="decreaseAmount"
        >
          <v-icon dark>
            mdi-minus
          </v-icon>

        </v-btn>
      </v-card>
      <div
          v-if="!title"

          class="search-something"
      >
        <p>Search Products</p>
        <v-icon
            x-large
            color="green darken-2"
        >
          mdi-magnify
        </v-icon>
      </div>

    </v-card>


    <v-card
        class="mx-auto pa-4 d-flex align-center  "
        max-width="400px"
        min-height="120px"
        outlined
        elevation="18"
    >

      <v-text-field
          autofocus
          class="mb-2"
          v-model.number="id"
          label="Enter Id"
          light
          flat
          clearable
          clear-icon="mdi-close-circle-outline"
          @keyup.enter="search"

      ></v-text-field>

      <v-btn
          :disabled="!id"
          class="mx-2" fab dark small color="teal"
          @click="search"

      >
        <v-icon light>
          mdi-magnify
        </v-icon>

      </v-btn>


    </v-card>
  </div>

</template>

<script>
import ProductService from "@/services/ProductService";

export default {
  name: 'Home',
  data() {
    return {title: null, id: null, amount: null}
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
              this.amount = null;
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
        this.$store.dispatch('product/addStockAmount', this.amount)
            .then(() => {
              this.title = this.$store.getters['product/getProduct']
              this.amount = null;
            })
      } catch (e) {
        console.log('there is a problem in refill')

      }
    },
    decreaseAmount() {
      try {
        this.$store.dispatch('product/decreaseStockAmount', this.amount).then(() => {
          this.title = this.$store.getters['product/getProduct'];
          this.amount = null;

        })
      } catch (e) {
        console.log('decrease amount problem')
      }
    }
  },



}
</script>


<style lang="scss">

.search-something {
  text-align: center;
  position: absolute;
  top: 70%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 50%;
}


</style>
