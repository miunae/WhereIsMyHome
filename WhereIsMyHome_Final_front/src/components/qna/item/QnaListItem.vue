<template>
  <!-- <div>
    <b-row
    class="m-6"
    @click="selectQna"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="12" class="align-self-center">번호 : {{ qna.qnano }} </b-col>
    <b-col cols="12" class="align-self-center">아이디 :{{ qna.userid }} </b-col>
    <b-col cols="12" class="align-self-center">{{ qna.subject }} </b-col>
  </b-row>
  <b-row
   class="m-6"
    @click="selectQna"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="12" class="align-self-center">등록 시간 :{{ qna.regtime }} </b-col>
    <b-col cols="12" class="align-self-center">{{ qna.hit }} </b-col>
    <b-col cols="12" class="align-self-center">{{ qna.content }} </b-col>
  </b-row>
    </div> -->
  <b-tr>
    <b-td>{{ qna.qnano }}</b-td>
    <b-th class="text-left" @click="selectQna">
      {{ qna.subject }}
    </b-th>
    <b-td>{{ qna.userid }}</b-td>
    <b-td>{{ qna.regtime }}</b-td>
    <b-td>{{ qna.hit }}</b-td>
  </b-tr>
</template>

<script>
import { mapActions } from "vuex";
const qnaboardStore = "qnaboardStore";

export default {
  name: "QnaListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    qna: Object,
  },
  methods: {
    ...mapActions(qnaboardStore, ["detailQna"]),
    selectQna() {
      console.log("detailQna");
      console.log(this.qna);
      this.detailQna(this.qna);
      // this.$router.push({name:"qnaview"});
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style>
.qna {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>