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
            <li ><a href="/CDD/main/{{homeSiape}}" onClick="window.location.reload()">Home</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios <span class="caret"></span></a>
                <ul class="dropdown-menu" style="max-height: 900px !important; min-width: 350% !important;">
                    <li><a href="/CDD/main/rltProfessor" onClick="window.location.reload()">Distribuição de disciplinas (ordenada por professor)</a></li>
                    <li><a href="/CDD/main/rltDisciplina" onClick="window.location.reload()">Distribuição de disciplinas (ordenada por disciplina)</a></li>
                    <li><a href="/CDD/main/rltCurso" onClick="window.location.reload()">Distribuição de disciplinas (ordenada por curso)</a></li>
                    <li><a href="/CDD/main/rltFilas" onClick="window.location.reload()">Todas as filas</a></li>
                    <li><a href="/CDD/main/rltFilas4" onClick="window.location.reload()">Disciplinas 4/4</a></li>
                    <li><a href="/CDD/main/rltFilas3" onClick="window.location.reload()">Disciplinas 3/4</a></li>
                    <li><a href="/CDD/main/rltTurmas" onClick="window.location.reload()">Turmas</a></li>
                </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</body>
</html>