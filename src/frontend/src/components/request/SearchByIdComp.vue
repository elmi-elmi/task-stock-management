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
        @input="expandHandling"
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
  data() {
    return {id: null}
  },
  emits:['value'],
  props: {
    label: {
      type: String,
      default: 'Enter Id'
    },
    value:{
      type:Boolean,
      default:false
    }
  },
  computed:{
    expandHandling(){
       this.$emit('input',!!this.id)
    }
  },
  methods: {

    sendRequest() {
      const requestToStore = this.$route.name === 'product'
          ? 'product/fetchProductById'
          : 'product/fetchStockById'

      this.$store.dispatch(requestToStore, this.id)
          .then(() => this.id = null)
          .catch((e) => {
            let name = '404Resource'
            if (e.code === "ERR_NETWORK") name = 'networkError'
            console.log(e)
            this.$router.push({name, params: {message: e.message,res:e.response.data}})
          })

    }
  },

}
</script>

<style scoped>

</style>
