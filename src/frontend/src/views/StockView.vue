<template>
  <div >
    <h1>This is stock page</h1>
    <SearchCardComp label="Search Stock" />
    {{stock}}

  </div>
</template>

<script>
import SearchCardComp from "@/components/SearchCardComp";
export default{
  components:{SearchCardComp},
  computed: {
    stock() {
      return this.$store.getters['product/getStock']
    }
  },
}
</script>
