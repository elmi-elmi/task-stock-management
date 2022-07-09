<template>
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
        v-model="id"
        :label="label"
        light
        flat
        clearable
        clear-icon="mdi-close-circle-outline"
        @keyup.enter="sendRequest"

    ></v-text-field>

    <v-btn
        :disabled="!id"
        class="mx-2" fab dark small color="teal"
        @click="sendRequest"

    >
      <v-icon light>
        mdi-magnify
      </v-icon>
    </v-btn>

  </v-card>
</template>

<script>
export default {
  name: "SearchCardComp",
  data(){return{id:null}},
  props:{
    label:{
      type:String,
      default:'Enter Id'
    }
  },
  methods:{
    sendRequest() {
      const requestToStore = this.$route.name==='product'
          ?'product/fetchProductById'
          :'product/fetchStockById'
      try {
        this.$store.dispatch(requestToStore, this.id)
            .then(() => {
              this.id = null
            }).catch((e) => {
          this.$router.push({name: 'notFound'})
        })

      } catch (e) {
        console.log(e)
        console.log('there is a problem (500)')

      }
    },

  }
}
</script>

<style scoped>

</style>
