<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <join-input-item />
  </b-container>
</template>

<script>
import JoinInputItem from "@/components/user/item/JoinInputItem";
export default {
  name: "UserRegister",
  components: {
    JoinInputItem,
  },
};
</script>

<style></style>
