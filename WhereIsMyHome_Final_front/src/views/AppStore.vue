<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Store Service
    </h3>
    <b-row>
      <b-col>
        <store-search></store-search>
      </b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>
<script>
import StoreSearch from "@/components/store/StoreSearch.vue";
export default {
  name: "AppStore",
  components: {
    StoreSearch,
  },
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
