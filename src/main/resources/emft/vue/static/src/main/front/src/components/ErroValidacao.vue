<template>
    <div v-if="mensagem" class="grupoErroValidacao">
        <span class="erroValidacao">
            {{mensagem}}
        </span>
    </div>
</template>
<script>
export default {
    props: ['mensagem']
}
</script>