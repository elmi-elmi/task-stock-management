<template>
  <v-card
      class="d-flex justify-center align-center mb-2 " flat tile
  >
    <v-text-field
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
</template>

<script>


export default {
  name: "InputAmountComp",
  data(){
    return{
      amount:null}
  },

  methods:{
    refill() {
      try {
        this.$store.dispatch('product/addStockAmount', this.amount)
            .then(() => {
              this.amount = null;
            })
      } catch (e) {
        console.log('there is a problem in refill')

      }
    },
    decreaseAmount() {
      try {
        this.$store.dispatch('product/decreaseStockAmount', this.amount).then(() => {
          this.amount = null;

        })
      } catch (e) {
        console.log('decrease amount problem')
      }
    }
  },
}
</script>

<style scoped>

</style>
