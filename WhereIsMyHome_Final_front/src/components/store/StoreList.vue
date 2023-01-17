<template>
  <div class="container mt-3">
    <h2 id="store-area-title" align="center">주변 상권</h2>
    <!-- <div id="map"></div> -->
    <store-map></store-map>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>상호명</th>
          <th>업종분류</th>
          <th>지번</th>
          <th>도로명주소</th>
          <th>위도</th>
          <th>경도</th>
        </tr>
      </thead>
      <tr :key="i" v-for="(store, i) in stores">
        <td>{{ store.storeName }}</td>
        <td>{{ store.storeSectorName }}</td>
        <td>{{ store.storeAddr }}</td>
        <td>{{ store.storeNAddr }}</td>
        <td>{{ store.storeLng }}</td>
        <td>{{ store.storeLat }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { mapState } from "vuex";
import StoreMap from "@/components/store/StoreMap.vue";
const storeStore = "storeStore";
export default {
  name: "StoreList",
  components: {
    StoreMap,
  },
  data() {
    return {};
  },
  mounted() {},
  computed: {
    ...mapState(storeStore, ["stores"]),
  },
  methods: {},
};
</script>

<style scoped></style>
