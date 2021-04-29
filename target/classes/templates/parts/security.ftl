<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        id = user.getId()
        name = user.getInformation().getPerson().getName()
        username = user.getUsername()
        isAdmin = user.isAdmin()
        isUser = user.isUserRole()
        isRecorted = user.isRecorted()
        isTablin = user.isTablin()
        isConfirm = user.isConfirm()
        isLog = true
    >
<#else>
    <#assign
        id = 0
        name = "unknown"
        username = "unknown"
        isAdmin = false
        isUser = false
        isRecorted = false
        isTablin = false
        isConfirm = false
        isLog = false
    >
</#if>
