<template>
    <div>
        <!-- {{reviewNo}} -->
        <div class="box mt-3 p-3" >
            <b-row>
                <b-col cols="8">
                    <div>
                        <div style="font-size:1.1em;font-weight:bold;" >{{review.fromUser}}</div>
                        <div style="font-size:0.9em">{{review.content}}</div>
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:1em;font-weight:bold;">{{review.date[0]}}.{{review.date[1]}}.{{review.date[2]}}</div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Review',
    props:["review"],
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
    },
};
</script>

<style scoped>
.box{
    border-radius:1em;
    background-color: #F6F9FB;
    box-shadow: 0.1em 0.1em gray;
}
</style>