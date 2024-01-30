<?php
    function get_soap_client() {
        return new SoapClient("http://localhost:8082/etudiants/WSEtudiant?wsdl");
    }

    function get_size() {
        try {
            $soap_client = get_soap_client();
            $none = new stdClass();
            return $soap_client->__soapCall("getSize", array($none));
        } catch (Exception $exception) {
            echo "Parsing WSDL Error : Couldn't load from 'http://localhost:8082/etudiants/WSEtudiant?wsdl' ";
        }
    }

    function get_moyenne($nom, $prenom) {
        try {
            $soap_client = get_soap_client();
            $args = new stdClass();
            $args->nom = $nom;
            $args->prenom = $prenom;
            return $soap_client->__soapCall("getMoyenne", array($args));
        } catch (Exception $exception) {
            echo "Parsing WSDL Error : Couldn't load from 'http://localhost:8082/etudiants/WSEtudiant?wsdl' ";
        }
    }

    function get_etudiants() {
        try {
            $soap_client = get_soap_client();
            $none = new stdClass();
            return $soap_client->__soapCall("getEtudiants", array($none));
        } catch (Exception $exception) {
            echo "Parsing WSDL Error : Couldn't load from 'http://localhost:8082/etudiants/WSEtudiant?wsdl' ";
        }
    }

    function create_etudiant($nom, $prenom, $moyenne) {
        try {
            $soap_client = get_soap_client();
            $args = new stdClass();
            $args->nom = $nom;
            $args->prenom = $prenom;
            $args->moyenne = $moyenne;
            return $soap_client->__soapCall("newStudent", array($args));
        } catch (Exception $exception) {
            echo "Erreur lors de la création de l'étudiant : ", $exception->getMessage();
        }
    }
    

    /* MAIN */
    $nom="";
    $prenom="";
    if(isset($_POST['operation'])) {
        $operation = $_POST['operation'];
        switch ($operation) {
            case "Nombre d'étudiants" : $nb_etudiants = get_size(); break;
            case "Liste des etudiants" : $etudiants = get_etudiants(); break;
            case "Moyenne" : 
                if(isset($_POST['nom']) && isset($_POST['prenom'])) {
                    $nom = $_POST['nom'];
                    $prenom = $_POST['prenom'];
                    $moyenne = get_moyenne($nom, $prenom);
                }
                break;
            case "Créer" : 
                if(isset($_POST['nouveau_nom']) && isset($_POST['nouveau_prenom']) && isset($_POST['nouvelle_moyenne'])) {
                    $nom = $_POST['nouveau_nom'];
                    $prenom = $_POST['nouveau_prenom'];
                    $moyenne = $_POST['nouvelle_moyenne'];
                    $create = create_etudiant($nom, $prenom, $moyenne);
                }
                break;
            default:;
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Etudiant SOAP</title>
</head>
<body>
    <div class="box">
        <form action="client_etudiant.php" method="post">
            <h4>Voir des statistiques</h4>

            <input name="operation" type="submit" value="Nombre d'étudiants" class="ft-spm" /> <br>
            <?php if(isset($nb_etudiants)) echo "Le nombre d'étudiants : ", $nb_etudiants->return ; ?>
            <br />
            
            <br />
            <h5>Trouver la moyenne d'un étudiant précis</h5>

            <span class="bold">Nom</span> : <br> 
            <input name="nom" type="text" value='<?php echo($nom)?>' class="my" /> <br>
            <span class="bold">Prénom</span> : <br> 
            <input name="prenom" type="text" value='<?php echo($prenom)?>' class="my" /> <br>


            <input name="operation" type="submit" value="Moyenne" class="my ft-spm" /> <br />
            <?php if(isset($moyenne)) echo "La moyenne de ", $nom, ' ' , $prenom, " est : ", $moyenne->return ; ?>
            <br />
            
            
            <h5>Créer un nouvel étudiant</h5>

            <span class="bold">Nom</span> : <br> 
            <input name="nouveau_nom" type="text" class="my" /> <br>
            <span class="bold">Prénom</span> : <br> 
            <input name="nouveau_prenom" type="text" class="my" /> <br>
            <span class="bold">Moyenne</span> : <br> 
            <input name="nouvelle_moyenne" type="text" class="my" /> <br>

            <input name="operation" type="submit" value="Créer" class="my ft-spm" /> <br />



            <br />
            <input name="operation" type="submit" value="Liste des etudiants" class="ft-spm" />
            <?php if(isset($etudiants)) { ?>
                <h1>Liste des etudiants : </h1>
                <table border="1" cellspacing="0" cellpadding="5">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Moyenne</th>
                    </tr>
                    <?php foreach($etudiants->return as $etudiant) { ?>
                        <tr>
                            <td><?php echo($etudiant->id); ?></td>
                            <td><?php echo($etudiant->nom); ?></td>
                            <td><?php echo($etudiant->prenom); ?></td>
                            <td><?php echo($etudiant->moyenne); ?></td>
                        </tr>
                    <?php } ?>
                </table>
            <?php } ?>
        </form>
    </div>
</body>
</html>