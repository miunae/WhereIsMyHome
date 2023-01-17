<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ house.aptName }}</h3>
      </b-col>
    </b-row>
    <b-row class="ml-1 mb-1">
      <!-- <b-col><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-col> -->
      <div id="map" style="width: 800px; height: 600px">
        <map-copy :house="house"></map-copy>
      </div>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.aptCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.dongName }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">경도 : {{ house.lng }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger">위도 : {{ house.lat }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <!-- <b-alert show variant="secondary"
          >최근 거래가 목록 : {{ }} housedeal에서 가져와야함</b-alert
        > -->
        <b-alert show variant="secondary" v-if="deals && deals.length != 0">
          최근 거래가 목록
          <b-table-simple hover responsive>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>거래일자</b-th>
                <b-th>층</b-th>
                <b-th>면적</b-th>
                <b-th>거래금액</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <house-deal-item
                v-for="(deal, index) in deals"
                :key="index"
                :deal="deal"
              />
            </tbody>
          </b-table-simple>
        </b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import HouseDealItem from "@/components/house/HouseDealItem.vue";
import MapCopy from "@/components/house/MapCopy.vue";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  components: {
    HouseDealItem,
    MapCopy,
  },
  computed: {
    ...mapState(houseStore, ["house", "deals"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style>
</style>
