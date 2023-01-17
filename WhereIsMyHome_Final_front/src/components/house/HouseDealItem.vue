<template>
  <b-tr>
    <b-td>{{deal.dealYear}}년{{deal.dealMonth}}월{{deal.dealDay}}일</b-td>
    <b-td>{{deal.floor}}층</b-td>
    <b-td>{{deal.area}}</b-td>
    <b-td>{{deal.dealAmount}}000원</b-td>
  </b-tr>
</template>

<script>

export default {
  name: "HouseDealItem",
  props: {
    deal: Object,
    index: String,
  },
}
</script>

<style>

</style>