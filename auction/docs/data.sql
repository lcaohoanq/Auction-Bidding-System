CREATE DATABASE IF NOT EXISTS auction_bidding_test;

USE auction_bidding_test;

INSERT INTO bid_methods (name) VALUES
                                   (0), -- FIXED_PRICE
                                   (1), -- SEALED_BID
                                   (2), -- ASCENDING_BID
                                   (3); -- DESCENDING_BID

INSERT INTO auction_status (name) VALUES
                                   (0), -- INCOMING
                                  (1), -- OPEN
                                  (2), -- CLOSED
                                  (3); -- CANCELLED