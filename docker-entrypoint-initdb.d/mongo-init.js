print('Start #################################################################');
// Conectar ao banco de dados 'admin' para criar o usuário admin
db = db.getSiblingDB('admin');

// Criação do usuário administrador
db.createUser({
  user: 'compassion', 
  pwd: 'compassion', 
  roles: [{ role: 'root', db: 'admin' }]
});

// Conectar ao banco de dados 'compassion' (se não existir, será criado)
db = db.getSiblingDB('compassion');

// Criação da collection 'users' com alguns dados de exemplo
db.createCollection('user');

// Confirmação de que o script foi executado corretamente
print('User admin created, compassion database and users collection created with sample data.');

print('finish #################################################################');