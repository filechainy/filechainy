<?php

class Block
{
    // Properties of a block
    public $index;
    public $timestamp;
    public $data;
    public $prevHash;
    public $hash;

    // Constructor for a block
    public function __construct($index, $timestamp, $data, $prevHash)
    {
        $this->index = $index;
        $this->timestamp = $timestamp;
        $this->data = $data;
        $this->prevHash = $prevHash;
        $this->hash = $this->calculateHash();
    }

    // Calculate the hash of a block
    public function calculateHash()
    {
        return hash('sha256', $this->index . $this->timestamp . $this->data . $this->prevHash);
    }
}

class Blockchain
{
    // Properties of the blockchain
    public $chain;

    // Constructor for the blockchain
    public function __construct()
    {
        $this->chain = [$this->createGenesisBlock()];
    }

    // Create the first block in the blockchain (called the "genesis block")
    public function createGenesisBlock()
    {
        return new Block(0, time(), 'Genesis Block', '0');
    }

    // Get the latest block in the blockchain
    public function getLatestBlock()
    {
        return $this->chain[count($this->chain) - 1];
    }

    // Add a new block to the blockchain
    public function addBlock($newBlock)
    {
        $newBlock->prevHash = $this->getLatestBlock()->hash;
        $newBlock->hash = $newBlock->calculateHash();
        $this->chain[] = $newBlock;
    }
}

// Create a new blockchain
$coin = new Blockchain();

// Add some blocks to the blockchain
$coin->addBlock(new Block(1, time(), 'Transaction 1: Bob gives Alice 1 bitcoin', $coin->getLatestBlock()->hash));
$coin->addBlock(new Block(2, time(), 'Transaction 2: Charlie gives Bob 1 bitcoin', $coin->getLatestBlock()->hash));
$coin->addBlock(new Block(3, time(), 'Transaction 3: Alice gives Charlie 1 bitcoin', $coin->getLatestBlock()->hash));

// Print the blockchain to the console
echo json_encode($coin, JSON_PRETTY_PRINT);
