<!DOCTYPE html>
<html>
<head>
  <%@ page contentType="text/html; charset=UTF-8" %>
  <link rel="stylesheet" href="../js/lib/tags-input/ng-tags-input.bootstrap.min.css">
  <script type="text/javascript" src="../js/lib/tags-input/ng-tags-input.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Distribuição de Disciplinas</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
          <ul class="nav navbar-nav">
            <li ><a href="/CDD" onClick="window.location.reload()">Home</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                <ul class="dropdown-menu" style="max-height: 900px !important;width: 270px !important;">
                    <li><a href="curso" onClick="window.location.reload()">Curso</a></li>
                    <li><a href="disciplina" onClick="window.location.reload()">Disciplina</a></li>
                    <li><a href="etapas" onClick="window.location.reload()">Restrição Etapas</a></li>
                    <li><a href="fila" onClick="window.location.reload()">Fila</a></li>
                    <li><a href="filaProfessor" onClick="window.location.reload()">Fila Professor</a></li>
                    <li><a href="horaTurma" onClick="window.location.reload()">Hora Turma</a></li>
                    <li><a href="ministraDisciplina" onClick="window.location.reload()">Atribuição de turmas aos professores</a></li>
                    <li><a href="prioridade" onClick="window.location.reload()">Prioridade</a></li>
                    <li><a href="professor" onClick="window.location.reload()">Professor</a></li>
                    <li><a href="restricao" onClick="window.location.reload()">Restrição</a></li>
                    <li><a href="semestre" onClick="window.location.reload()">Semestre</a></li>
                    <li><a href="turma" onClick="window.location.reload()">Turma</a></li>
                </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</body>
</html>