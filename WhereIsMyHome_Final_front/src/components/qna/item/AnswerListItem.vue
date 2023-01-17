<template>
    <div>
    <b-row
    class="m-6"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="12" class="align-self-center">번호 : {{ answer.ansNo }} </b-col>
    <b-col cols="12" class="align-self-center">아이디 :{{ answer.userid }} </b-col>
    <b-col cols="12" class="align-self-center">{{ answer.subject }} </b-col>
  </b-row>
  <b-row
   class="m-6"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="12" class="align-self-center">등록 시간 :{{ answer.regtime }} </b-col>
    <b-col cols="12" class="align-self-center">{{ answer.content }} </b-col>
  </b-row>
  <b-button @click="deleteAnswer(answer.ansNo)" v-if="answer.userid === this.userInfo.userid">답변삭제</b-button>

    </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const qnaboardStore = "qnaboardStore";
const memberStore = "memberStore";
export default {
  computed:{
    ...mapState(qnaboardStore,["qna"]),
    ...mapState(memberStore, ["userInfo"]),
  },
    name:"answerListItem",
    data() {
        return {

            isColor:false,
        };
    },
    props:{
      answer:Object,
    },
    created(){
      console.log(this.answer.content);
    },
    methods:{
        ...mapActions(qnaboardStore, ["deleteAnswer"]),
        colorChange(flag){
            this.isColor=flag;
        },

    }
}
</script>

<style>
.answer {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>