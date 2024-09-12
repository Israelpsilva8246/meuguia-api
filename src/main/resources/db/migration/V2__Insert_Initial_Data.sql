-- Inserindo dados na tabela TouristSegmentation com descrições
INSERT INTO tourist_segmentation (id, name, description)
VALUES
(1, 'Turismo de base comunitária', 'Atividades turísticas geridas por comunidades locais, promovendo integração cultural.'),
(2, 'Turismo de eventos e negócios (técnicos-científico e/ou profissional)', 'Viagens relacionadas a eventos profissionais, técnicos ou científicos.'),
(3, 'Turismo de entretenimento e lazer', 'Turismo voltado para atividades de lazer, como parques e atrações recreativas.'),
(4, 'Turismo de sol e praia', 'Turismo em regiões litorâneas, focado em praias e atividades ao ar livre.'),
(5, 'Turismo de esportes ou turismo náutico', 'Turismo voltado para práticas esportivas ou atividades aquáticas.'),
(6, 'Turismo gastronômico', 'Exploração de destinos focados na culinária local e experiências gastronômicas.'),
(7, 'Turismo histórico-cultural (patrimônio histórico-cultural material e/ou imaterial)', 'Visitas a locais históricos e culturais, incluindo patrimônio material e imaterial.'),
(8, 'Turismo macabro ou Dark Turism', 'Visitação de locais associados a eventos trágicos ou de morte.'),
(9, 'Turismo pedagógico ou educacional', 'Viagens educacionais com foco em aprendizado, como excursões escolares.'),
(10, 'Turismo religioso ou peregrinação', 'Turismo relacionado a práticas religiosas ou peregrinações a locais sagrados.'),
(11, 'Turismo rural ou ecoturismo ou turismo de aventura', 'Turismo em áreas rurais, com foco em natureza, ecologia e atividades de aventura.');


-- Inserindo dados na tabela AttractionType com descrições
INSERT INTO attraction_type (id, name, description)
VALUES
(12, 'Aldeia indígena', 'Comunidades indígenas abertas à visitação para imersão cultural.'),
(13, 'Biblioteca', 'Espaço de leitura e consulta de livros e documentos.'),
(14, 'Colégio/Escola', 'Instituições de ensino com relevância histórica ou cultural.'),
(15, 'Cachoeira', 'Queda de água natural, geralmente localizada em áreas turísticas.'),
(16, 'Cemitério', 'Local de sepultamento com importância histórica ou cultural.'),
(17, 'Centro de artesanato', 'Espaços destinados à produção e venda de artesanato local.'),
(18, 'Empreendimento de esportes/lazer', 'Locais voltados para atividades esportivas e recreativas.'),
(19, 'Empreendimento de eventos (Teatro/Casa de Shows/Centro de convenções)', 'Locais dedicados a apresentações artísticas, shows ou convenções.'),
(20, 'Engenho', 'Instalações históricas usadas para a produção de açúcar e cachaça.'),
(21, 'Estabelecimento gastronômico', 'Restaurantes ou locais focados na culinária regional.'),
(22, 'Fortaleza/Forte', 'Construções militares antigas com relevância histórica.'),
(23, 'Formação rochosa', 'Estruturas geológicas naturais que atraem turistas por sua beleza ou singularidade.'),
(24, 'Floresta/Bosque', 'Áreas florestais protegidas que oferecem trilhas e contato com a natureza.'),
(25, 'Igreja', 'Templos religiosos de importância arquitetônica ou histórica.'),
(26, 'Lago/Lagoa/Barragem', 'Corpos de água naturais ou artificiais, populares para turismo ecológico.'),
(27, 'Meio de Hospedagem', 'Hotéis, pousadas e outros estabelecimentos para hospedagem de turistas.'),
(28, 'Museu', 'Instituições que preservam e exibem artefatos históricos, culturais ou científicos.'),
(29, 'Parque', 'Áreas recreativas ao ar livre, muitas vezes com infraestrutura turística.'),
(31, 'Praia', 'Área litorânea destinada ao turismo e lazer, com águas do mar.'),
(32, 'Praça pública', 'Espaços urbanos abertos, frequentemente com relevância histórica ou social.'),
(33, 'Quilombo', 'Comunidades de descendentes de escravos, preservando tradições culturais.'),
(34, 'Rio/Riacho', 'Corpos de água naturais, com potencial para turismo ecológico e recreativo.'),
(35, 'Sítios Arqueológicos', 'Locais de importância arqueológica com vestígios de civilizações antigas.'),
(36, 'Unidade de Conservação/Preservação Ambiental', 'Áreas protegidas para a preservação da biodiversidade e ecossistemas.');

-- Inserindo dados na tabela Attraction
INSERT INTO attraction (id, name, description, map_link, city, state, image_link, fonte)
VALUES
(39, 'Praia do Jacaré', 'A Praia do Jacaré é famosa pelo pôr do sol ao som do Bolero de Ravel.', 'https://goo.gl/maps/1', 'Cabedelo', 'Paraíba', 'https://image_link_jacare.com', 'Observatório de Turismo da Paraíba'),
(40, 'Farol do Cabo Branco', 'O Farol do Cabo Branco marca o ponto mais oriental das Américas.', 'https://goo.gl/maps/2', 'João Pessoa', 'Paraíba', 'https://image_link_farol.com', 'Observatório de Turismo da Paraíba');


-- Inserindo dados na tabela MoreInfoLink
INSERT INTO more_info_link (id, link, description)
VALUES
(37, 'https://site_praia_jacare.com', 'site_praia_jacare'),
(38, 'https://site_farol_cabo_branco.com', 'site_farol_cabo_branco');

-- Relacionando Attraction e TouristSegmentation (Many-to-Many)
INSERT INTO attraction_segmentation (attraction_id, segmentation_id)
VALUES
(39, 1),  -- Praia do Jacaré é Ecoturismo
(39, 2),  -- Praia do Jacaré também é Cultural
(39, 3),  -- Farol do Cabo Branco é Cultural
(40, 4);  -- Farol do Cabo Branco também é Aventura

-- Relacionando Attraction e AttractionType (One-to-One)
INSERT INTO attraction_attractiontype (attraction_id, attractiontype_id)
VALUES
(40, 12),  -- Praia do Jacaré é uma Praia
(39, 15);  -- Farol do Cabo Branco é um Monumento Histórico

