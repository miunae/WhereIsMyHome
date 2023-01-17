<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon>News Service
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10"> </b-col>
      <b-col></b-col>
    </b-row>
    <br /><br /><br /><br />
    <news-list></news-list>
  </b-container>
</template>

<script>
import NewsList from "@/components/news/NewList.vue";

export default {
  name: "AppMain",
  components: {
    NewsList,
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
