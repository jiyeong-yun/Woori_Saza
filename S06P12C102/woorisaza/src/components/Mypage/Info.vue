<template>
    <div>
        <b-container>
            <div>
                닉네임
                <b-form-input v-model="nickname" placeholder="닉네임을 입력하세요."></b-form-input>
            </div>
            <div class="mt-4">
                주소<br>
                <b-form-input v-model="postcode" placeholder="우편번호" style="width:70%;display:inline" disabled></b-form-input>
                <b-button variant="warning" @click="execDaumPostcode" class="ml-3" style="width:20%;display:inline">우편번호 찾기</b-button>
                <b-form-input v-model="address" placeholder="주소" class="mt-1" disabled></b-form-input>
            </div>

            <div class="mt-4">
                프로필사진
                <div style="text-align:center">
                    <div class="photo" style="display:inline-block"> 
                        <div v-if="preImage==null">
                            <br><br>
                            <img  src="@/assets/IDimage.png" alt="">
                        </div>
                        <div v-if="preImage!=null">
                            <br>
                            <img :src="preImage" alt="" style="width : 140px; height:140px">
                        </div>
                    </div>
                    <div class="mt-1">
                        <b-form-file v-model="image" plain @change="registerImage" accept=".jpg, .png"></b-form-file>
                    </div>
                </div>
            </div>
            <div class="mt-4" style="text-align:center">
                <!-- 탈퇴 수정 버튼 -->
                <b-button variant="secondary" class="mr-3" @click="deleteUser">회원 탈퇴</b-button>
                <b-button variant="warning" class="ml-3" @click="updateUser">정보 수정</b-button>
            </div>
        </b-container>
        
    </div>
</template>

<script>
import {axios_contact} from "@/common.js"
export default {
    name: 'Info',

    data() {
        return {
            nickname : null,
            postcode:null,
            address : null,
            id : this.$cookie.get("id"),
            userProfile : null,
            image : null,
            preImage : null,
            imageChange : false,
        };
    },

    created() {
        axios_contact({
            method : "get",
            url : "/user/"+this.id,
        }).then(({data})=>{
            // console.log(data)
            data
            this.userProfile = data.profile;
            this.nickname = this.userProfile.nickname;
            this.address = this.userProfile.address;
            // this.image = this.userProfile.pic;
            this.preImage = this.userProfile.pic;
        })
    },

    mounted() {
        
    },

    methods: {
        execDaumPostcode() {
            new window.daum.Postcode({
                oncomplete: (data) => {
                if (this.extraAddress !== "") {
                    this.extraAddress = "";
                }
                if (data.userSelectedType === "R") {
                    // 사용자가 도로명 주소를 선택했을 경우
                    this.address = data.roadAddress;
                } else {
                    // 사용자가 지번 주소를 선택했을 경우(J)
                    this.address = data.jibunAddress;
                }
        
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === "R") {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    this.extraAddress += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== "" && data.apartment === "Y") {
                    this.extraAddress +=
                        this.extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (this.extraAddress !== "") {
                    this.extraAddress = `(${this.extraAddress})`;
                    }
                } else {
                    this.extraAddress = "";
                }
                // 우편번호를 입력한다.
                this.postcode = data.zonecode;
                },
            }).open();
        },

        deleteUser(){
            if(confirm("정말 탈퇴하시겠습니까?")==true){
                axios_contact({
                    method : "delete",
                    url : "/user/delete",
                    data : {
                        id : this.id
                    }
                }).then(({data})=>{
                    console.log(data)
                    this.$cookie.delete("Raccesstoken");
                    this.$cookie.delete("accesstoken");
                    this.$cookie.delete("id");
                    this.$router.push("/");
                    this.$router.go("");
                })
            }
        },

        registerImage(event){
            var input = event.target;
            if(input.files && input.files[0]){
                var reader = new FileReader();
                reader.onload = (e) =>{
                    this.preImage = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
            this.imageChange = true;
        },

        updateUser(){
            // console.log("수정해줘 벅벅");
            // console.log(this.image);
            if(this.imageChange){
                const formData = new FormData();
                formData.append('uploadFile', this.image);
                // console.log(formData);

                axios_contact({
                    method : "post",
                    url : "/user/upload",
                    headers : {
                        'Content-Type': 'multipart/form-data',
                    },
                    data : formData,
                }).then(({data})=>{
                    this.userProfile.pic = data.url;
                    this.userProfile.address = this.address;
                    this.userProfile.nickname = this.nickname;
                    axios_contact({
                        method : "put",
                        url : "/user/update",
                        data : this.userProfile,
                    }).then(({data})=>{
                        console.log(data)
                        this.$router.go();
                    })
                })
            }
            else{
                this.userProfile.address = this.address;
                this.userProfile.nickname = this.nickname;
                axios_contact({
                    method : "put",
                    url : "/user/update",
                    data : this.userProfile,
                }).then(({data})=>{
                    console.log(data)
                    this.$router.go();
                })
            }
        },
    },
};
</script>

<style scoped>
.btn-warning{
    width : 10em;
    background-color: #F1A501 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #F1A501;
    border-radius: 2em;
}
.btn-secondary{
    width : 10em;
    background-color: #C4C4C4 ;
    color : white;
    font-size : 0.5em;
    padding: 0.5em;
    border-color: #C4C4C4;
    border-radius: 2em;
}
.photo{
    background-color: white;
    width:180px;
    height: 180px;
    border: 1px solid black;
}
</style>